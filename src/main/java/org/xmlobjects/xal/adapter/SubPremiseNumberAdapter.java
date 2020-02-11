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

package org.xmlobjects.xal.adapter;

import org.xmlobjects.builder.ObjectBuildException;
import org.xmlobjects.builder.ObjectBuilder;
import org.xmlobjects.serializer.ObjectSerializeException;
import org.xmlobjects.serializer.ObjectSerializer;
import org.xmlobjects.stream.XMLReadException;
import org.xmlobjects.stream.XMLReader;
import org.xmlobjects.stream.XMLWriteException;
import org.xmlobjects.stream.XMLWriter;
import org.xmlobjects.xal.model.IndicatorOccurrence;
import org.xmlobjects.xal.model.NumberTypeOccurrence;
import org.xmlobjects.xal.model.SubPremiseNumber;
import org.xmlobjects.xal.util.XALConstants;
import org.xmlobjects.xml.Attributes;
import org.xmlobjects.xml.Element;
import org.xmlobjects.xml.Namespaces;

import javax.xml.namespace.QName;

public class SubPremiseNumberAdapter implements ObjectBuilder<SubPremiseNumber>, ObjectSerializer<SubPremiseNumber> {

    @Override
    public SubPremiseNumber createObject(QName name) throws ObjectBuildException {
        return new SubPremiseNumber();
    }

    @Override
    public void initializeObject(SubPremiseNumber object, QName name, Attributes attributes, XMLReader reader) throws ObjectBuildException, XMLReadException {
        reader.getTextContent().ifPresent(object::setContent);
        attributes.getValue("Indicator").ifPresent(object::setIndicator);
        attributes.getValue("IndicatorOccurrence").ifPresent(v -> object.setIndicatorOccurrence(IndicatorOccurrence.fromValue(v)));
        attributes.getValue("NumberTypeOccurrence").ifPresent(v -> object.setNumberTypeOccurrence(NumberTypeOccurrence.fromValue(v)));
        attributes.getValue("PremiseNumberSeparator").ifPresent(object::setPremiseNumberSeparator);
        attributes.getValue("Type").ifPresent(object::setType);
        attributes.getValue("Code").ifPresent(object::setCode);
        XALBuilderHelper.buildOtherAttributes(object.getOtherAttributes(), attributes);
    }

    @Override
    public Element createElement(SubPremiseNumber object, Namespaces namespaces) throws ObjectSerializeException {
        return Element.of(XALConstants.XAL_2_0_NAMESPACE, "SubPremiseNumber");
    }

    @Override
    public void initializeElement(Element element, SubPremiseNumber object, Namespaces namespaces, XMLWriter writer) throws ObjectSerializeException, XMLWriteException {
        element.addTextContent(object.getContent());
        element.addAttribute("Indicator", object.getIndicator());
        element.addAttribute("PremiseNumberSeparator", object.getPremiseNumberSeparator());
        element.addAttribute("Type", object.getType());
        element.addAttribute("Code", object.getCode());

        if (object.getIndicatorOccurrence() != null)
            element.addAttribute("IndicatorOccurrence", object.getIndicatorOccurrence().toValue());

        if (object.getNumberTypeOccurrence() != null)
            element.addAttribute("NumberTypeOccurrence", object.getNumberTypeOccurrence().toValue());

        XALSerializerHelper.serializeOtherAttributes(element, object.getOtherAttributes());
    }
}
