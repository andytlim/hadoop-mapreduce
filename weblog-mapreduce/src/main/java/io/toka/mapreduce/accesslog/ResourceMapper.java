package io.toka.mapreduce.accesslog;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class ResourceMapper extends Mapper<LongWritable, Text, Text, IntWritable> {

	@Override
	public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
		String line = value.toString();
		
		int index = line.indexOf("/assets");
		
		if (index > -1) {
			line = line.substring(index);
			line = line.substring(0, line.indexOf(" "));		
		
			context.write(new Text(line), new IntWritable(1));
		}
	}
}
