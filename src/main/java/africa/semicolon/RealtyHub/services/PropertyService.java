package africa.semicolon.RealtyHub.services;

import africa.semicolon.RealtyHub.dtos.requests.SearchPropertyRequest;
import africa.semicolon.RealtyHub.dtos.requests.UploadPropertyRequest;
import africa.semicolon.RealtyHub.dtos.requests.ViewPropertyListingRequest;
import africa.semicolon.RealtyHub.dtos.requests.ViewPropertyRequest;
import africa.semicolon.RealtyHub.dtos.response.SearchPropertyResponse;
import africa.semicolon.RealtyHub.dtos.response.UploadPropertyResponse;
import africa.semicolon.RealtyHub.dtos.response.ViewPropertyListingResponse;
import africa.semicolon.RealtyHub.dtos.response.ViewPropertyResponse;
import africa.semicolon.RealtyHub.exceptions.InvalidRequestException;
import africa.semicolon.RealtyHub.exceptions.UserNotFoundException;

public interface PropertyService {

    UploadPropertyResponse uploadProperty(UploadPropertyRequest request) throws UserNotFoundException;

    ViewPropertyResponse viewProperty(ViewPropertyRequest request);

    SearchPropertyResponse searchProperty(SearchPropertyRequest request) throws InvalidRequestException;

    ViewPropertyListingResponse viewProperties(ViewPropertyListingRequest request);

}
