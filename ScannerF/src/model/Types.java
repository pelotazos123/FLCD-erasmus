package model;

public enum Types {



    CHAR("[LETTER{1}|DIGIT{1}]"),
    IDENTIFIER("[LETTER]|[LETTER \\{LETTER\\} \\{DIGIT\\}]"),
    LETTER("[a-zA-Z]"),

    DOUBLE("[SIGN]DIGIT [DIGIT] [COMMA DIGIT \\{DIGIT\\}]"),

    DIGIT("[0-9]+"),
    SIGN("('+'|'-')"),
    INTEGER("[[SIGN]DIGIT]"),

    COMMA(","),

    UNDERLINE("_"),

    OPERATORS("[+|\\-|\\*|/|%|~|&|\\(|)|^|<<|>>|!|\\(||)|\\?|\\==|!=|\\++|\\--|<=|>|>=|\"|\\+=|\\-=]"),


    RESERVED_WORDS("break,case,char,const,final,default,do,while,if," +
            "else,double,float,int,bool,long,short,for,println,return,switch,void,try,catch,var"),
    SEPARATORS("['\\['|'\\]'|'\\{'|'\\}'|;|:|\s]");



    public final String pattern;

    Types(String s) {
        this.pattern = s;
    }


}
