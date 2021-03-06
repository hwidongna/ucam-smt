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
package uk.ac.cam.eng.extraction.hadoop.merge;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Partitioner;
import org.apache.hadoop.mapreduce.lib.partition.HashPartitioner;

import uk.ac.cam.eng.extraction.hadoop.datatypes.AlignmentAndFeatureMap;
import uk.ac.cam.eng.extraction.hadoop.datatypes.RuleWritable;

/**
 * 
 * @author Aurelien Waite
 * @date 28 May 2014
 */
class MergePartitioner extends
		Partitioner<RuleWritable, AlignmentAndFeatureMap> {

	private Partitioner<Text, AlignmentAndFeatureMap> defaultPartitioner = new HashPartitioner<>();

	@Override
	public int getPartition(RuleWritable key, AlignmentAndFeatureMap value,
			int numPartitions) {
		return defaultPartitioner.getPartition(key.getSource(), value,
				numPartitions);
	}

}