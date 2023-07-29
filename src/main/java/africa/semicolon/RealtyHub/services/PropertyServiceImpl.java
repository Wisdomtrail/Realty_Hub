package africa.semicolon.RealtyHub.services;

import africa.semicolon.RealtyHub.dtos.requests.*;
import africa.semicolon.RealtyHub.dtos.response.SearchPropertyResponse;
import africa.semicolon.RealtyHub.dtos.response.UploadPropertyResponse;
import africa.semicolon.RealtyHub.dtos.response.ViewPropertyListingResponse;
import africa.semicolon.RealtyHub.dtos.response.ViewPropertyResponse;
import africa.semicolon.RealtyHub.exceptions.InvalidRequestException;
import africa.semicolon.RealtyHub.exceptions.UserNotFoundException;
import africa.semicolon.RealtyHub.models.HouseDetail;
import africa.semicolon.RealtyHub.models.Property;
import africa.semicolon.RealtyHub.models.User;
import africa.semicolon.RealtyHub.repositories.PropertyRepository;
import africa.semicolon.RealtyHub.repositories.UserRepository;
import jakarta.el.PropertyNotFoundException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Root;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static africa.semicolon.RealtyHub.utils.AppUtils.USER_WITH_ID_NOT_FOUND;
import static africa.semicolon.RealtyHub.utils.ExceptionUtils.*;
import static africa.semicolon.RealtyHub.utils.ResponseUtils.PROPERTY_UPLOAD_SUCCESSFUL;

@AllArgsConstructor
@Service
public class PropertyServiceImpl implements PropertyService{

    private PropertyRepository propertyRepository;
    private UserRepository userRepository;
    private ModelMapper modelMapper;
    private EntityManager entityManager;

    @Override
    public UploadPropertyResponse uploadProperty(UploadPropertyRequest request) throws UserNotFoundException {
        Long userId = request.getUserId();
        System.out.println(userId);
        Optional<User> optionalUser = userRepository.findById(userId);
        User user = optionalUser.orElseThrow(() -> new UserNotFoundException(String.format(USER_WITH_ID_NOT_FOUND, userId)));
        System.out.println(user.getBioData().getEmail());
        Property property = new Property();
        property.setUser(user);
        modelMapper.map(request, property);
        propertyRepository.save(property);
        return buildUploadPropertyResponse(property.getId());
    }

    private UploadPropertyResponse buildUploadPropertyResponse(Long id) {
        UploadPropertyResponse response = new UploadPropertyResponse();
        response.setId(id);
        response.setMessage(PROPERTY_UPLOAD_SUCCESSFUL);
        return response;
    }

    @Override
    public ViewPropertyResponse viewProperty(ViewPropertyRequest request) {
        Long id = request.getId();
        Optional<Property> property = propertyRepository.findById(id);
        Property foundProperty = property.orElseThrow(() -> new PropertyNotFoundException(String.format(PROPERTY_NOT_FOUND, id)));
        return buildViewPropertyResponse(foundProperty);
    }

    private ViewPropertyResponse buildViewPropertyResponse(Property foundProperty) {
        ViewPropertyResponse response = new ViewPropertyResponse();
        modelMapper.map(foundProperty,response);
        return response;
    }

    @Override
    public SearchPropertyResponse searchProperty(SearchPropertyRequest request) throws InvalidRequestException {
        BigDecimal price = request.getPrice();
        String state = request.getState();
        if (price != null){
            return findPropertyByPrice(price);

        }
        if (state != null){
            findPropertyByState(state);
        }
        throw new InvalidRequestException(INVALID_REQUEST);
    }


    private  SearchPropertyResponse findPropertyByState(String state) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Property> query = cb.createQuery(Property.class);
        Root<Property> root = query.from(Property.class);
        query.where(cb.equal(root.get("state"), state));
        TypedQuery<Property> typedQuery = entityManager.createQuery(query);
        List<Property> properties = typedQuery.getResultList();
        List<Property> filteredProperties = new ArrayList<>();
        for (Property property : properties) {
            if (Objects.equals(property.getState(), state)){
                filteredProperties.add(property);
            }
        }
        return buildSearchPropertyResponse(filteredProperties);
    }

    private SearchPropertyResponse buildSearchPropertyResponse(List<Property> filteredProperties) {
        SearchPropertyResponse response = new SearchPropertyResponse();
        for (Property property: filteredProperties) {
            PropertyDto propertyDto = new PropertyDto();
            modelMapper.map(property, propertyDto);
            response.addPropertyDto(propertyDto);
        }
        return response;
    }

    private SearchPropertyResponse findPropertyByPrice(BigDecimal price) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Property> query = cb.createQuery(Property.class);
        Root<Property> propertyRoot = query.from(Property.class);
        Join<Property, HouseDetail> houseDetailsJoin = propertyRoot.join("houseDetail");
        query.where(cb.equal(houseDetailsJoin.get("price"), price));
        TypedQuery<Property> typedQuery = entityManager.createQuery(query);
        List<Property> properties = typedQuery.getResultList();
        return buildSearchPropertyResponse(properties);
    }

    @Override
    public ViewPropertyListingResponse viewProperties(ViewPropertyListingRequest request) {
        int page = request.getPage();
        int pageSize = request.getPageSize();
        PageRequest pageRequest = PageRequest.of(page-1, pageSize);
        Page<Property> properties = propertyRepository.findAll(pageRequest);
        List<Property> propertyList = properties.getContent();
        return buildViewListingResponse(propertyList);
    }

    private ViewPropertyListingResponse buildViewListingResponse(List<Property> propertyList) {
        ViewPropertyListingResponse response = new ViewPropertyListingResponse();
        for (Property property: propertyList) {
            PropertyDto propertyDto = new PropertyDto();
            modelMapper.map(property, propertyDto);
            response.addPropertyDto(propertyDto);
        }
        return response;
    }
}
