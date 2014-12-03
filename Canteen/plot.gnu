set datafile sep ','
set terminal png
set output 'graph.png'
plot 'results.csv' using 1:3 with line title 'incomingPersons','results.csv' using 1:4 with line title 'foodQueue','results.csv' using 1:5 with line title 'tableQueue','results.csv' using 1:6 with line title 'eating'
