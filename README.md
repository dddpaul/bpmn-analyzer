# BPMN Process Analyzer

This program analyzes BPMN processes and extracts information about the process and its tasks.

## Usage

### Build and run fat jar with Java 21

To use this program, you have to build fat jar with ```make build``` command. Then execute it:

```bash
java -jar build/libs/bpmn-analyzer-0.0.1-SNAPSHOT-all.jar -p [process_file]
```
This will read the contents of the specified file and extract information about the process and its tasks. The resulting JSON string is printed to the console.

### Options

The program supports the following options:

```
-p: Specify the name of the BPMN process file to be analyzed
-h: Display a help message and exit.
```

### Run docker image

A more convenient way is to run docker image and pass a BPMN file to STDIN:

```bash
cat [process_file] | docker run -i dddpaul/bpmn-analyzer:v0.5.0
```

To pretty output use ```jq```:

```bash
cat [process_file] | docker run -i dddpaul/bpmn-analyzer:v0.5.0 | jq .
```
