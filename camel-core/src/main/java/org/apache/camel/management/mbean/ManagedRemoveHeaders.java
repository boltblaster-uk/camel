/**
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.camel.management.mbean;

import org.apache.camel.CamelContext;
import org.apache.camel.api.management.ManagedResource;
import org.apache.camel.api.management.mbean.ManagedRemoveHeadersMBean;
import org.apache.camel.model.ProcessorDefinition;
import org.apache.camel.processor.RemoveHeadersProcessor;

/**
 * @version 
 */
@ManagedResource(description = "Managed RemoveHeaders")
public class ManagedRemoveHeaders extends ManagedProcessor implements ManagedRemoveHeadersMBean {
    private final RemoveHeadersProcessor processor;
    private final String exclude;

    public ManagedRemoveHeaders(CamelContext context, RemoveHeadersProcessor processor, ProcessorDefinition<?> definition) {
        super(context, processor, definition);
        this.processor = processor;
        if (processor.getExcludePattern() != null) {
            exclude = processor.getExcludePattern().toString();
        } else {
            exclude = null;
        }
    }

    @Override
    public String getPattern() {
        return processor.getPattern();
    }

    @Override
    public String getExcludePattern() {
        return exclude;
    }
}
