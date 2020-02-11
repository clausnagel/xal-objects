/*
 * xal-objects - A Java mapping for the OASIS eXtensible Address Language (xAL)
 * https://github.com/xmlobjects/xal-objects
 *
 * Copyright 2019-2020 Claus Nagel <claus.nagel@gmail.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.xmlobjects.xal.visitor;

import org.xmlobjects.xal.model.Address;
import org.xmlobjects.xal.model.AddressDetails;
import org.xmlobjects.xal.model.AddressIdentifier;
import org.xmlobjects.xal.model.AddressLatitude;
import org.xmlobjects.xal.model.AddressLatitudeDirection;
import org.xmlobjects.xal.model.AddressLine;
import org.xmlobjects.xal.model.AddressLines;
import org.xmlobjects.xal.model.AddressLongitude;
import org.xmlobjects.xal.model.AddressLongitudeDirection;
import org.xmlobjects.xal.model.AdministrativeArea;
import org.xmlobjects.xal.model.AdministrativeAreaName;
import org.xmlobjects.xal.model.Barcode;
import org.xmlobjects.xal.model.BuildingName;
import org.xmlobjects.xal.model.Country;
import org.xmlobjects.xal.model.CountryName;
import org.xmlobjects.xal.model.CountryNameCode;
import org.xmlobjects.xal.model.Department;
import org.xmlobjects.xal.model.DepartmentName;
import org.xmlobjects.xal.model.DependentLocality;
import org.xmlobjects.xal.model.DependentLocalityName;
import org.xmlobjects.xal.model.DependentLocalityNumber;
import org.xmlobjects.xal.model.DependentThoroughfare;
import org.xmlobjects.xal.model.EndorsementLineCode;
import org.xmlobjects.xal.model.Firm;
import org.xmlobjects.xal.model.FirmName;
import org.xmlobjects.xal.model.KeyLineCode;
import org.xmlobjects.xal.model.LargeMailUser;
import org.xmlobjects.xal.model.LargeMailUserIdentifier;
import org.xmlobjects.xal.model.LargeMailUserName;
import org.xmlobjects.xal.model.Locality;
import org.xmlobjects.xal.model.LocalityName;
import org.xmlobjects.xal.model.MailStop;
import org.xmlobjects.xal.model.MailStopName;
import org.xmlobjects.xal.model.MailStopNumber;
import org.xmlobjects.xal.model.PostBox;
import org.xmlobjects.xal.model.PostBoxNumber;
import org.xmlobjects.xal.model.PostBoxNumberExtension;
import org.xmlobjects.xal.model.PostBoxNumberPrefix;
import org.xmlobjects.xal.model.PostBoxNumberSuffix;
import org.xmlobjects.xal.model.PostOffice;
import org.xmlobjects.xal.model.PostOfficeName;
import org.xmlobjects.xal.model.PostOfficeNumber;
import org.xmlobjects.xal.model.PostTown;
import org.xmlobjects.xal.model.PostTownName;
import org.xmlobjects.xal.model.PostTownSuffix;
import org.xmlobjects.xal.model.PostalCode;
import org.xmlobjects.xal.model.PostalCodeNumber;
import org.xmlobjects.xal.model.PostalCodeNumberExtension;
import org.xmlobjects.xal.model.PostalRoute;
import org.xmlobjects.xal.model.PostalRouteName;
import org.xmlobjects.xal.model.PostalRouteNumber;
import org.xmlobjects.xal.model.PostalServiceElements;
import org.xmlobjects.xal.model.Premise;
import org.xmlobjects.xal.model.PremiseLocation;
import org.xmlobjects.xal.model.PremiseName;
import org.xmlobjects.xal.model.PremiseNumber;
import org.xmlobjects.xal.model.PremiseNumberPrefix;
import org.xmlobjects.xal.model.PremiseNumberRange;
import org.xmlobjects.xal.model.PremiseNumberRangeFrom;
import org.xmlobjects.xal.model.PremiseNumberRangeTo;
import org.xmlobjects.xal.model.PremiseNumberSuffix;
import org.xmlobjects.xal.model.SortingCode;
import org.xmlobjects.xal.model.SubAdministrativeArea;
import org.xmlobjects.xal.model.SubAdministrativeAreaName;
import org.xmlobjects.xal.model.SubPremise;
import org.xmlobjects.xal.model.SubPremiseLocation;
import org.xmlobjects.xal.model.SubPremiseName;
import org.xmlobjects.xal.model.SubPremiseNumber;
import org.xmlobjects.xal.model.SubPremiseNumberPrefix;
import org.xmlobjects.xal.model.SubPremiseNumberSuffix;
import org.xmlobjects.xal.model.SupplementaryPostalServiceData;
import org.xmlobjects.xal.model.Thoroughfare;
import org.xmlobjects.xal.model.ThoroughfareLeadingType;
import org.xmlobjects.xal.model.ThoroughfareName;
import org.xmlobjects.xal.model.ThoroughfareNumber;
import org.xmlobjects.xal.model.ThoroughfareNumberFrom;
import org.xmlobjects.xal.model.ThoroughfareNumberPrefix;
import org.xmlobjects.xal.model.ThoroughfareNumberRange;
import org.xmlobjects.xal.model.ThoroughfareNumberSuffix;
import org.xmlobjects.xal.model.ThoroughfareNumberTo;
import org.xmlobjects.xal.model.ThoroughfarePostDirection;
import org.xmlobjects.xal.model.ThoroughfarePreDirection;
import org.xmlobjects.xal.model.ThoroughfareTrailingType;
import org.xmlobjects.xal.model.XAL;

public interface XALVisitor {
    void visit(Address address);
    void visit(AddressDetails addressDetails);
    void visit(AddressIdentifier addressIdentifier);
    void visit(AddressLatitude addressLatitude);
    void visit(AddressLatitudeDirection addressLatitudeDirection);
    void visit(AddressLine addressLine);
    void visit(AddressLines addressLines);
    void visit(AddressLongitude addressLongitude);
    void visit(AddressLongitudeDirection addressLongitudeDirection);
    void visit(AdministrativeArea administrativeArea);
    void visit(AdministrativeAreaName administrativeAreaName);
    void visit(Barcode barcode);
    void visit(BuildingName buildingName);
    void visit(Country country);
    void visit(CountryName countryName);
    void visit(CountryNameCode countryNameCode);
    void visit(Department department);
    void visit(DepartmentName departmentName);
    void visit(DependentLocality dependentLocality);
    void visit(DependentLocalityName dependentLocalityName);
    void visit(DependentLocalityNumber dependentLocalityNumber);
    void visit(DependentThoroughfare dependentThoroughfare);
    void visit(EndorsementLineCode endorsementLineCode);
    void visit(Firm firm);
    void visit(FirmName firmName);
    void visit(KeyLineCode keyLineCode);
    void visit(LargeMailUser largeMailUser);
    void visit(LargeMailUserIdentifier largeMailUserIdentifier);
    void visit(LargeMailUserName largeMailUserName);
    void visit(Locality locality);
    void visit(LocalityName localityName);
    void visit(MailStop mailStop);
    void visit(MailStopName mailStopName);
    void visit(MailStopNumber mailStopNumber);
    void visit(PostalCode postalCode);
    void visit(PostalCodeNumber postalCodeNumber);
    void visit(PostalCodeNumberExtension postalCodeNumberExtension);
    void visit(PostalRoute postalRoute);
    void visit(PostalRouteName postalRouteName);
    void visit(PostalRouteNumber postalRouteNumber);
    void visit(PostalServiceElements postalServiceElements);
    void visit(PostBox postBox);
    void visit(PostBoxNumber postBoxNumber);
    void visit(PostBoxNumberExtension postBoxNumberExtension);
    void visit(PostBoxNumberPrefix postBoxNumberPrefix);
    void visit(PostBoxNumberSuffix postBoxNumberSuffix);
    void visit(PostOffice postOffice);
    void visit(PostOfficeName postOfficeName);
    void visit(PostOfficeNumber postOfficeNumber);
    void visit(PostTown postTown);
    void visit(PostTownName postTownName);
    void visit(PostTownSuffix postTownSuffix);
    void visit(Premise premise);
    void visit(PremiseLocation premiseLocation);
    void visit(PremiseName premiseName);
    void visit(PremiseNumber premiseNumber);
    void visit(PremiseNumberPrefix premiseNumberPrefix);
    void visit(PremiseNumberRange premiseNumberRange);
    void visit(PremiseNumberRangeFrom premiseNumberRangeFrom);
    void visit(PremiseNumberRangeTo premiseNumberRangeTo);
    void visit(PremiseNumberSuffix premiseNumberSuffix);
    void visit(SortingCode sortingCode);
    void visit(SubAdministrativeArea subAdministrativeArea);
    void visit(SubAdministrativeAreaName subAdministrativeAreaName);
    void visit(SubPremise subPremise);
    void visit(SubPremiseLocation subPremiseLocation);
    void visit(SubPremiseName subPremiseName);
    void visit(SubPremiseNumber subPremiseNumber);
    void visit(SubPremiseNumberPrefix subPremiseNumberPrefix);
    void visit(SubPremiseNumberSuffix subPremiseNumberSuffix);
    void visit(SupplementaryPostalServiceData supplementaryPostalServiceData);
    void visit(Thoroughfare thoroughfare);
    void visit(ThoroughfareLeadingType thoroughfareLeadingType);
    void visit(ThoroughfareName thoroughfareName);
    void visit(ThoroughfareNumber thoroughfareNumber);
    void visit(ThoroughfareNumberFrom thoroughfareNumberFrom);
    void visit(ThoroughfareNumberPrefix thoroughfareNumberPrefix);
    void visit(ThoroughfareNumberRange thoroughfareNumberRange);
    void visit(ThoroughfareNumberSuffix thoroughfareNumberSuffix);
    void visit(ThoroughfareNumberTo thoroughfareNumberTo);
    void visit(ThoroughfarePostDirection thoroughfarePostDirection);
    void visit(ThoroughfarePreDirection thoroughfarePreDirection);
    void visit(ThoroughfareTrailingType thoroughfareTrailingType);
    void visit(XAL xal);
}
