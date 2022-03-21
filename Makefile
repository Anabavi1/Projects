JFLAGS = -g
JC = javac
.SUFFIXES: .java .class
.java.class:
	$(JC) $(JFLAGS) $*.java
CLASSES = nabavian_p1.java State.java Path.java

default: classes

classes: $(CLASSES:.java=.class)

clean:
	$(RM) *.class
