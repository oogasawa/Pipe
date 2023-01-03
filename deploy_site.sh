#!/bin/bash

mvn javadoc:javadoc
rm -Rf ~/public_html/javadoc/Pipe
mv target/site ~/public_html/javadoc/Pipe
