grammar Hello;

prog
    :
    level_num '9' HELLO ID '!'
    ;

level_num
    :
    NUM | '9'
    ;

compilation_unit
    :
    prog+ EOF
    ;

HELLO : H E L L O ;

NUM : [0-9]+ ;

ID : [a-zA-Z0-9]+ ;

LINE_COMMIT : '//' .*? '\r'? '\n' -> channel(HIDDEN);

WS : [\t\r\n]+ -> skip;