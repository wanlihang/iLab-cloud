package com.ilab.service.constant;

public class TaskExecutionScriptConstant {
    /**
     * 执行 python 任务
     */
    public static final String PYTHON_SCRIPT = "python main.py";

    /**
     * 执行 java 任务 1
     */
    public static final String JAVA_SCRIPT_WORDCOUNT = "java -jar WordCount.jar data/t8.shakespeare.txt";

    /**
     * 执行 java 任务 2
     */
    public static final String JAVA_SCRIPT_PAGERANK = "java -jar PageRank.jar data/web-Google.txt";

    /**
     * 执行 java 任务 3
     */
    public static final String JAVA_SCRIPT_MAPREDUCE = "java -jar  MapReduce.jar data/xxxx";

}
