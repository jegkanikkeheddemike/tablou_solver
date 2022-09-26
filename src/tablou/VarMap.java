package tablou;

import java.util.HashMap;

import tablou.solver.values.Atomic;


//Det var ærligt talt et dåtligt valg at værdien i hashmappet er en Atomic. 
//Det skulle i praksis havde været en Maybe<boolean>, siden det ikke er en
//god ide at have TablouVærdien og resultatet som samme klasse, da de ikke
//har så meget med hinanden at gøre. Se rust implementationen af samme problem,
//der bruges et Map<String,Option<bool>>. Hvilket bedre representer dataen.

//Oh well. For sent at ændre nu

public class VarMap extends HashMap<String, Atomic> {


    //Man har akut brug for en deep clone. Ikke shallow clone.
    @Override
    public VarMap clone() {

        VarMap clone = new VarMap();

        for (String key : keySet()) {
            clone.put(key + "", this.get(key).clone());
        }

        return clone;
    }
}
