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

package org.xmlobjects.xal.adapter.deprecated;

import org.xmlobjects.builder.ObjectBuildException;
import org.xmlobjects.model.Child;
import org.xmlobjects.serializer.ObjectSerializeException;
import org.xmlobjects.stream.XMLReadException;
import org.xmlobjects.stream.XMLReader;
import org.xmlobjects.stream.XMLWriteException;
import org.xmlobjects.stream.XMLWriter;
import org.xmlobjects.xal.adapter.AddressObjectAdapter;
import org.xmlobjects.xal.adapter.deprecated.types.AddressLineAdapter;
import org.xmlobjects.xal.adapter.deprecated.types.ThoroughfareNameAdapter;
import org.xmlobjects.xal.adapter.deprecated.types.ThoroughfareNumberAdapter;
import org.xmlobjects.xal.adapter.deprecated.types.ThoroughfareNumberPrefixAdapter;
import org.xmlobjects.xal.adapter.deprecated.types.ThoroughfareNumberRangeAdapter;
import org.xmlobjects.xal.adapter.deprecated.types.ThoroughfareNumberSuffixAdapter;
import org.xmlobjects.xal.adapter.deprecated.types.ThoroughfarePostDirectionAdapter;
import org.xmlobjects.xal.adapter.deprecated.types.ThoroughfarePreDirectionAdapter;
import org.xmlobjects.xal.model.Address;
import org.xmlobjects.xal.model.FreeTextAddress;
import org.xmlobjects.xal.model.PostCode;
import org.xmlobjects.xal.model.Premises;
import org.xmlobjects.xal.model.Thoroughfare;
import org.xmlobjects.xal.model.types.ThoroughfareNameOrNumber;
import org.xmlobjects.xal.util.XALConstants;
import org.xmlobjects.xml.Attributes;
import org.xmlobjects.xml.Element;
import org.xmlobjects.xml.Namespaces;

import javax.xml.namespace.QName;

public class ThoroughfareAdapter extends AddressObjectAdapter<Thoroughfare> {

    @Override
    public Thoroughfare createObject(QName name, Object parent) throws ObjectBuildException {
        Thoroughfare object = new Thoroughfare();
        if (parent instanceof Child)
            object.setParent((Child) parent);

        return object;
    }

    @Override
    public void initializeObject(Thoroughfare object, QName name, Attributes attributes, XMLReader reader) throws ObjectBuildException, XMLReadException {
        super.initializeObject(object, name, attributes, reader);
        attributes.getValue("Type").ifPresent(object::setType);
        attributes.getValue("DependentThoroughfares").ifPresent(v -> object.getOtherAttributes().add("DependentThoroughfares", v));
        attributes.getValue("DependentThoroughfaresIndicator").ifPresent(v -> object.getOtherAttributes().add("DependentThoroughfaresIndicator", v));
        attributes.getValue("DependentThoroughfaresConnector").ifPresent(v -> object.getOtherAttributes().add("DependentThoroughfaresConnector", v));
        attributes.getValue("DependentThoroughfaresType").ifPresent(v -> object.getOtherAttributes().add("DependentThoroughfaresType", v));
    }

    @Override
    public void buildChildObject(Thoroughfare object, QName name, Attributes attributes, XMLReader reader) throws ObjectBuildException, XMLReadException {
        if (XALConstants.XAL_2_0_NAMESPACE.equals(name.getNamespaceURI())) {
            Address address = object.getParent(Address.class);
            switch (name.getLocalPart()) {
                case "AddressLine": {
                    if (address != null) {
                        if (address.getFreeTextAddress() == null)
                            address.setFreeTextAddress(new FreeTextAddress());

                        address.getFreeTextAddress().getAddressLines().add(reader.getObjectUsingBuilder(AddressLineAdapter.class));
                    }
                    break;}
                case "ThoroughfareNumber":
                    object.getNameElementOrNumber().add(new ThoroughfareNameOrNumber(reader.getObjectUsingBuilder(ThoroughfareNumberAdapter.class)));
                    break;
                case "ThoroughfareNumberRange":
                    object.getNameElementOrNumber().addAll(reader.getObjectUsingBuilder(ThoroughfareNumberRangeAdapter.class));
                    break;
                case "ThoroughfareNumberPrefix":
                    object.getNameElementOrNumber().add(new ThoroughfareNameOrNumber(reader.getObjectUsingBuilder(ThoroughfareNumberPrefixAdapter.class)));
                    break;
                case "ThoroughfareNumberSuffix":
                    object.getNameElementOrNumber().add(new ThoroughfareNameOrNumber(reader.getObjectUsingBuilder(ThoroughfareNumberSuffixAdapter.class)));
                    break;
                case "ThoroughfarePreDirection":
                    object.getNameElementOrNumber().add(new ThoroughfareNameOrNumber(reader.getObjectUsingBuilder(ThoroughfarePreDirectionAdapter.class)));
                    break;
                case "ThoroughfarePostDirection":
                    object.getNameElementOrNumber().add(new ThoroughfareNameOrNumber(reader.getObjectUsingBuilder(ThoroughfarePostDirectionAdapter.class)));
                    break;
                case "ThoroughfareLeadingType":
                case "ThoroughfareTrailingType":
                case "ThoroughfareName":
                    object.getNameElementOrNumber().add(new ThoroughfareNameOrNumber(reader.getObjectUsingBuilder(ThoroughfareNameAdapter.class)));
                    break;
                case "DependentThoroughfare":
                    object.getSubThoroughfares().add(reader.getObjectUsingBuilder(DependentThoroughfareAdapter.class));
                    break;
                case "DependentLocality":
                    object.getDeprecatedProperties().setDependentLocality(reader.getObjectUsingBuilder(DependentLocalityAdapter.class));
                    break;
                case "Premise":
                    Premises premise = reader.getObjectUsingBuilder(PremiseAdapter.class);
                    if (address != null && address.getPremises() == null)
                        address.setPremises(premise);
                    else
                        object.getDeprecatedProperties().setPremise(premise);
                    break;
                case "Firm":
                    object.getDeprecatedProperties().setFirm(reader.getObjectUsingBuilder(FirmAdapter.class));
                    break;
                case "PostalCode":
                    PostCode postalCode = reader.getObjectUsingBuilder(PostalCodeAdapter.class);
                    if (address != null && address.getPostCode() == null)
                        address.setPostCode(postalCode);
                    else
                        object.getDeprecatedProperties().setPostalCode(postalCode);
                    break;
            }
        }
    }

    @Override
    public void writeChildElements(Thoroughfare object, Namespaces namespaces, XMLWriter writer) throws ObjectSerializeException, XMLWriteException {

        for (ThoroughfareNameOrNumber nameElementOrNumber : object.getNameElementOrNumber()) {
            if (nameElementOrNumber.isSetNumber())
                writer.writeElementUsingSerializer(Element.of(XALConstants.XAL_2_0_NAMESPACE, "ThoroughfareNumber"), nameElementOrNumber.getNumber(), ThoroughfareNumberAdapter.class, namespaces);
        }
    }
}