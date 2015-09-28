package io.toka.mapreduce.accesslog;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

public class ResourceAccessCounter extends Configured implements Tool {

	public static void main(String[] args) throws Exception {
		int exitCode = ToolRunner.run(new Configuration(), new ResourceAccessCounter(), args);
		System.exit(exitCode);
	}
	
	public int run(String[] args) throws Exception {
		
		if (args.length != 2) {
			System.out.println("Usage: ResourceAccessCounter <input dir> <output dir>\n");
			return -1;
		}
		
		Job job = new Job(getConf());
		job.setJarByClass(ResourceAccessCounter.class);
		job.setJobName("Resource Access Counter");
		
		FileInputFormat.setInputPaths(job,  new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));
		
		job.setMapperClass(ResourceMapper.class);
		
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(IntWritable.class);
		
		job.setReducerClass(SumReducer.class);
		
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(IntWritable.class);		
		
		boolean success = job.waitForCompletion(true);		
		return success ? 1 : 0;
	}
}
