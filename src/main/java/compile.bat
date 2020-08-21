dir /s /B *.java > sources.txt
javac -sourcepath @sources.txt

java com\wtc\avaj\Simulator.java scenario.txt