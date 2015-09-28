package io.toka.mapreduce.accesslog;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class IdentityMapper extends Mapper<LongWritable, Text, IntWritable, Text> {

	@Override
	public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
		String[] fields = value.toString().split("\\s+");
		context.write(new IntWritable(Integer.parseInt(fields[1])), new Text(fields[0]));
	}
}
