package io.toka.persona.mr;

import io.toka.persona.service.AniYoshiSubs;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AnimeSubMapper extends Mapper<LongWritable, Text, Text, Text> {

	protected static Logger log = LoggerFactory.getLogger(AnimeSubMapper.class);
	
	@Override
	public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
		String line = value.toString();
		
		int index = line.indexOf("Dialogue");
		
		if (index > -1) {
			String name = AniYoshiSubs.getName(line);
			String dialogue = AniYoshiSubs.getDialogue(line);
				
			if (!name.equals("")) {
				log.info(name + " || " + dialogue);
				context.write(new Text(name), new Text(dialogue));
			}
		}
	}
}
