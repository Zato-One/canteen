set datafile sep ','
set terminal png size 2048,1536
set grid ytics lc rgb "#bbbbbb" lw 1 lt 0
set grid xtics lc rgb "#bbbbbb" lw 1 lt 0
set grid mxtics mytics
set mytics 10
set mxtics 10
set grid
set output 'graph.png'
plot 'results.csv' using 1:3 with line title 'incomingPersons','results.csv' using 1:4 with line title 'foodQueue','results.csv' using 1:5 with line title 'tableQueue','results.csv' using 1:6 with line title 'eating','results.csv' using 1:8 with line title 'averageWaitingTime'
