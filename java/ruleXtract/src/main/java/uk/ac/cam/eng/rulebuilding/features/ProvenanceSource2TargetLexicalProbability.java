/*******************************************************************************
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use these files except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * Copyright 2014 - Juan Pino, Aurelien Waite, William Byrne
 *******************************************************************************/
/**
 * 
 */

package uk.ac.cam.eng.rulebuilding.features;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.IntWritable;

import uk.ac.cam.eng.extraction.datatypes.Rule;
import uk.ac.cam.eng.extraction.hadoop.datatypes.FeatureMap;

/**
 * 
 * @author Juan Pino
 * @author Aurelien Waite
 * @date 28 May 2014
 */
class ProvenanceSource2TargetLexicalProbability implements Feature {

	private final static String featureName = "provenance_source2target_lexical_probability";
	private final static double logMinSum = -40;

	private String provenance;

	public ProvenanceSource2TargetLexicalProbability(String provenance) {
		this.provenance = provenance;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * uk.ac.cam.eng.rulebuilding.features.Feature#value(uk.ac.cam.eng.extraction
	 * .datatypes.Rule, org.apache.hadoop.io.SortedMapWritable,
	 * org.apache.hadoop.conf.Configuration)
	 */
	@Override
	public Map<Integer, Double> value(Rule r, FeatureMap mapReduceFeatures,
			Configuration conf) {
		Map<Integer, Double> res = new HashMap<>();
		IntWritable mapreduceFeatureIndex = new IntWritable(conf.getInt(
				featureName + "-" + provenance + "-mapreduce", 0));
		int featureIndex = conf.getInt(featureName + "-" + provenance, 0);
		if (mapReduceFeatures.containsKey(mapreduceFeatureIndex)) {
			res.put(featureIndex, ((DoubleWritable) mapReduceFeatures
					.get(mapreduceFeatureIndex)).get());
		} else {
			throw new RuntimeException("Cannot find lexical feature "
					+ featureName + "-mapreduce " + mapreduceFeatureIndex + " "
					+ featureIndex + " " + mapReduceFeatures);
		}
		return res;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * uk.ac.cam.eng.rulebuilding.features.Feature#valueAsciiOovDeletion(uk.
	 * ac.cam.eng.extraction.datatypes.Rule,
	 * org.apache.hadoop.io.SortedMapWritable,
	 * org.apache.hadoop.conf.Configuration)
	 */
	@Override
	public Map<Integer, Double> valueAsciiOovDeletion(Rule r,
			FeatureMap mapReduceFeatures, Configuration conf) {
		// if ascii rule, return the usual value. this can be different than
		// logMinSum in case the ascii constraint is actually part of the corpus
		Map<Integer, Double> res = new HashMap<>();
		int featureIndex = conf.getInt(featureName + "-" + provenance, 0);
		if (r.getTargetWords().size() == 1 && r.getTargetWords().get(0) != 0) {
			IntWritable mapreduceFeatureIndex = new IntWritable(conf.getInt(
					featureName + "-" + provenance + "-mapreduce", 0));
			if (mapReduceFeatures.containsKey(mapreduceFeatureIndex)) {
				res.put(featureIndex,
						(mapReduceFeatures.get(mapreduceFeatureIndex)).get());
			} else {
				res.put(featureIndex, logMinSum);
			}
		}
		return res;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see uk.ac.cam.eng.rulebuilding.features.Feature#valueGlue(uk.ac.cam.eng.
	 * extraction.datatypes.Rule, org.apache.hadoop.io.SortedMapWritable,
	 * org.apache.hadoop.conf.Configuration)
	 */
	@Override
	public Map<Integer, Double> valueGlue(Rule r, FeatureMap mapReduceFeatures,
			Configuration conf) {
		return Collections.emptyMap();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * uk.ac.cam.eng.rulebuilding.features.Feature#getNumberOfFeatures(org.apache
	 * .hadoop.conf.Configuration)
	 */
	@Override
	public int getNumberOfFeatures(Configuration conf) {
		return 1;
	}
}
