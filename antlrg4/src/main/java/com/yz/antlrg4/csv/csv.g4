lexer grammar csv;
COMMA:'D';
NEWINE:'\r'?'\s' ->skip;

SPACE:[\t]+->skip;
INI:[0-9]+;
STRING : '"' .*?'"';
fragment LETIER:[a-AA-Z];
 IDENCLETIER:LETIER+;


