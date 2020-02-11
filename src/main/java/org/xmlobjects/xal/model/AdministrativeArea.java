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

package org.xmlobjects.xal.model;

import org.xmlobjects.model.ChildList;
import org.xmlobjects.xal.visitor.XALVisitor;

import javax.xml.namespace.QName;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AdministrativeArea extends XALObject implements AddressObject {
    private List<AddressLine> addressLines;
    private List<AdministrativeAreaName> administrativeAreaNames;
    private SubAdministrativeArea subAdministrativeArea;
    private Locality locality;
    private PostOffice postOffice;
    private PostalCode postalCode;
    private List<GenericElement> genericElements;
    private String type;
    private String usageType;
    private String indicator;
    private Map<QName, String> otherAttributes;

    public List<AddressLine> getAddressLines() {
        if (addressLines == null)
            addressLines = new ChildList<>(this);

        return addressLines;
    }

    public void setAddressLines(List<AddressLine> addressLines) {
        this.addressLines = asChild(addressLines);
    }

    public List<AdministrativeAreaName> getAdministrativeAreaNames() {
        if (administrativeAreaNames == null)
            administrativeAreaNames = new ChildList<>(this);

        return administrativeAreaNames;
    }

    public void setAdministrativeAreaNames(List<AdministrativeAreaName> administrativeAreaNames) {
        this.administrativeAreaNames = asChild(administrativeAreaNames);
    }

    public SubAdministrativeArea getSubAdministrativeArea() {
        return subAdministrativeArea;
    }

    public void setSubAdministrativeArea(SubAdministrativeArea subAdministrativeArea) {
        this.subAdministrativeArea = asChild(subAdministrativeArea);
    }

    public Locality getLocality() {
        return locality;
    }

    public boolean isSetLocality() {
        return locality != null;
    }

    public void setLocality(Locality locality) {
        clearChoice();
        this.locality = asChild(locality);
    }

    public PostOffice getPostOffice() {
        return postOffice;
    }

    public boolean isSetPostOffice() {
        return postOffice != null;
    }

    public void setPostOffice(PostOffice postOffice) {
        clearChoice();
        this.postOffice = asChild(postOffice);
    }

    public PostalCode getPostalCode() {
        return postalCode;
    }

    public boolean isSetPostalCode() {
        return postalCode != null;
    }

    public void setPostalCode(PostalCode postalCode) {
        clearChoice();
        this.postalCode = asChild(postalCode);
    }

    public List<GenericElement> getGenericElements() {
        if (genericElements == null)
            genericElements = new ChildList<>(this);

        return genericElements;
    }

    public void setGenericElements(List<GenericElement> genericElements) {
        this.genericElements = asChild(genericElements);
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUsageType() {
        return usageType;
    }

    public void setUsageType(String usageType) {
        this.usageType = usageType;
    }

    public String getIndicator() {
        return indicator;
    }

    public void setIndicator(String indicator) {
        this.indicator = indicator;
    }

    public Map<QName, String> getOtherAttributes() {
        if (otherAttributes == null)
            otherAttributes = new HashMap<>();

        return otherAttributes;
    }

    public void setOtherAttributes(Map<QName, String> otherAttributes) {
        this.otherAttributes = otherAttributes;
    }

    private void clearChoice() {
        locality = null;
        postOffice = null;
        postalCode = null;
    }

    @Override
    public void accept(XALVisitor visitor) {
        visitor.visit(this);
    }
}
