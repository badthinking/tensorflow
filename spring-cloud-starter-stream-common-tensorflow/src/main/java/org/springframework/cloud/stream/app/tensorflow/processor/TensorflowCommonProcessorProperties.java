/*
 * Copyright 2017-2018 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.cloud.stream.app.tensorflow.processor;

import java.util.List;
import javax.validation.constraints.NotNull;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.io.Resource;
import org.springframework.expression.Expression;
import org.springframework.validation.annotation.Validated;

/**
 * Holds configuration properties for the TensorFlow Processor module.
 *
 * @author Christian Tzolov
 * @author Artem Bilan
 */
@ConfigurationProperties("tensorflow")
@Validated
public class TensorflowCommonProcessorProperties {

	/**
	 * The location of the pre-trained TensorFlow model file. The file, http and classpath schemas are supported. For
	 * archive locations takes the first file with '.pb' extension. Use the URI fragment parameter to specify an
	 * exact model name (e.g. https://foo/bar/model.tar.gz#frozen_inference_graph.pb)
	 */
	private Resource model;

	/**
	 * The TensorFlow graph model outputs. Comma separate list of TensorFlow operation names to fetch the output Tensors from.
	 */
	private List<String> modelFetch;

	/**
	 * How to obtain the input data from the input message. If empty it defaults to the input message payload.
	 * The payload.myInTupleName expression treats the input payload as a Tuple, and myInTupleName stands for
	 * a Tuple key. The headers[myHeaderName] expression to get input data from message's header using
	 * myHeaderName as a key.
	 */
	private Expression expression;

	/**
	 * Defines how to store the output data and if the input payload is passed through or discarded.
	 * Payload (Default) stores the output data in the outbound message payload. The input payload is discarded.
	 * Header stores the output data in outputName message's header. The the input payload is passed through.
	 * Tuple stores the output data in an Tuple payload, using the outputName key. The input payload is passed through
	 * in the same Tuple using the 'original.input.data'. If the input payload is already a Tuple that contains
	 * a 'original.input.data' key, then copy the input Tuple into the new Tuple to be returned.
	 */
	private OutputMode mode = OutputMode.payload;

	/**
	 * The output data key used in the Header or Tuple modes.
	 */
	private String outputName = "result";

	public List<String> getModelFetch() {
		return this.modelFetch;
	}

	public void setModelFetch(List<String> modelFetch) {
		this.modelFetch = modelFetch;
	}

	@NotNull
	public Resource getModel() {
		return this.model;
	}

	public void setModel(Resource model) {
		this.model = model;
	}

	public Expression getExpression() {
		return this.expression;
	}

	public void setExpression(Expression expression) {
		this.expression = expression;
	}

	@NotNull
	public OutputMode getMode() {
		return this.mode;
	}

	public void setMode(OutputMode mode) {
		this.mode = mode;
	}

	public String getOutputName() {
		return this.outputName;
	}

	public void setOutputName(String outputName) {
		this.outputName = outputName;
	}

}
