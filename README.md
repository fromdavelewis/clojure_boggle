# clojure_boggle

This is a toy application to experiment with Clojure and idiomatically
functional programming.

Invoked as:

clj clojure_boggle/src/clojure_boggle/core.clj

where clj is the wrapper script from installing clojure via homebrew:

#!/bin/sh
# Clojure wrapper script.
# With no arguments runs Clojure's REPL.

# Put the Clojure jar from the cellar and the current folder in the classpath.
CLOJURE=$CLASSPATH:/usr/local/Cellar/clojure/1.4.0/clojure-1.4.0.jar:${PWD}

if [ "$#" -eq 0 ]; then
    java -cp "$CLOJURE" clojure.main --repl
else
    java -cp "$CLOJURE" clojure.main "$@"
fi


The dictionary file, words.txt, originates from http://homepage.ntlworld.com/adam.bozon/Dictionary.htm

I'm still figuring out the clojure distribution niceties -- currently
the clojure 1.3.0 jar is included in lib/ but that's not ideal.

