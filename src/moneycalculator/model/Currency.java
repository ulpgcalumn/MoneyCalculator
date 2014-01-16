package moneycalculator.model;

public class Currency {
    private String code;
    private String name;
    private String symbol;

    public Currency(String code, String name) {
        this(code, name, "");
    }
    
    public Currency(String code, String name, String symbol) {
        this.code = code;
        this.name = name;
        this.symbol = symbol;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public String getSymbol() {
        return symbol;
    }

    @Override
    public String toString() {
        return name + "(" + code + ")";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass())
            return false;
        
        final Currency other = (Currency) obj;
        
        if (!this.code.equalsIgnoreCase(other.code)) return false;
        if (!this.name.equalsIgnoreCase(other.name)) return false;
        if (!this.symbol.equalsIgnoreCase(other.symbol)) return false;

        return true;
    }
    
}