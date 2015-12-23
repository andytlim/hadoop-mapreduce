package io.toka.persona.mr;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AnimeSub extends Configured implements Tool {

	protected static Logger log = LoggerFactory.getLogger(AnimeSub.class);
	
	public static void main(String[] args) throws Exception {
		int exitCode = ToolRunner.run(new Configuration(), new AnimeSub(), args);
		System.exit(exitCode);
	}
	
	public int run(String[] args) throws Exception {
		
		if (args.length != 2) {
			log.info("Usage: DialogueCounter <input dir> <output dir>\n");
			return -1;
		}
		
		Job job = Job.getInstance();
		job.setJarByClass(AnimeSub.class);
		job.setJobName("Dialogue Counter");
		
		FileInputFormat.setInputPaths(job,  new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));
		
		job.setMapperClass(AnimeSubMapper.class);
		
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(Text.class);
		
		job.setNumReduceTasks(0);
		
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(Text.class);		
		
		boolean success = job.waitForCompletion(true);		
		return success ? 1 : 0;
	}
}
