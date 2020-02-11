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
import org.xmlobjects.xal.model.SubPremiseNumberSuffix;
import org.xmlobjects.xal.util.XALConstants;
import org.xmlobjects.xml.Attributes;
import org.xmlobjects.xml.Element;
import org.xmlobjects.xml.Namespaces;

import javax.xml.namespace.QName;

public class SubPremiseNumberSuffixAdapter implements ObjectBuilder<SubPremiseNumberSuffix>, ObjectSerializer<SubPremiseNumberSuffix> {

    @Override
    public SubPremiseNumberSuffix createObject(QName name) throws ObjectBuildException {
        return new SubPremiseNumberSuffix();
    }

    @Override
    public void initializeObject(SubPremiseNumberSuffix object, QName name, Attributes attributes, XMLReader reader) throws ObjectBuildException, XMLReadException {
        reader.getTextContent().ifPresent(object::setContent);
        attributes.getValue("NumberSuffixSeparator").ifPresent(object::setNumberSuffixSeparator);
        attributes.getValue("Type").ifPresent(object::setType);
        attributes.getValue("Code").ifPresent(object::setCode);
        XALBuilderHelper.buildOtherAttributes(object.getOtherAttributes(), attributes);
    }

    @Override
    public Element createElement(SubPremiseNumberSuffix object, Namespaces namespaces) throws ObjectSerializeException {
        return Element.of(XALConstants.XAL_2_0_NAMESPACE, "SubPremiseNumberSuffix");
    }

    @Override
    public void initializeElement(Element element, SubPremiseNumberSuffix object, Namespaces namespaces, XMLWriter writer) throws ObjectSerializeException, XMLWriteException {
        element.addTextContent(object.getContent());
        element.addAttribute("NumberSuffixSeparator", object.getNumberSuffixSeparator());
        element.addAttribute("Type", object.getType());
        element.addAttribute("Code", object.getCode());
        XALSerializerHelper.serializeOtherAttributes(element, object.getOtherAttributes());
    }
}
