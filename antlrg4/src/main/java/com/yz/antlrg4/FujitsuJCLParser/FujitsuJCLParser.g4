grammar FujitsuJCLParser;


r : 'hello' ID ;
ID : [a-z]+ ;
WS : [ \t\n]+ -> skip ; // skip spaces, tabs, newlines
