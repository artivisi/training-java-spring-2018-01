package id.artivisi.belajar.belajarspringboot.dao;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.core.types.dsl.StringPath;
import id.artivisi.belajar.belajarspringboot.domain.Produk;
import id.artivisi.belajar.belajarspringboot.dto.KriteriaPencarian;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class ProdukPredicateBuilder {
    private static final String GT = ">";
    private static final String LT = "<";
    private static final String EQ = ":";
    private static final String CONTAINS = "~";
    
    private List<KriteriaPencarian> daftarKriteria = new ArrayList<>();
    private List<BooleanExpression> daftarPredicate = new ArrayList<>();
    
    public ProdukPredicateBuilder(List<KriteriaPencarian> daftarKriteria) {
        this.daftarKriteria = daftarKriteria;
    }
    
    public BooleanExpression build(){
        PathBuilder<Produk> entityPath = new PathBuilder<>(Produk.class, "produk");
        
        for(KriteriaPencarian k : daftarKriteria) {
            System.out.println("Nama variabel : "+k.getNama());
            System.out.println("Operator : "+k.getOperator());
            System.out.println("Nilai : "+k.getNilai());
            if(isNumeric(k.getNilai())) {
                NumberPath<BigDecimal> path = entityPath.getNumber(k.getNama(), BigDecimal.class);
                BigDecimal nilai = new BigDecimal(k.getNilai().toString());
                String operator = k.getOperator();
                BooleanExpression expr = null;
                if(GT.equals(operator)){
                    expr = path.gt(nilai);
                } else if(LT.equals(operator)){
                    expr = path.lt(nilai);
                }
                
                if(expr != null) {
                    daftarPredicate.add(expr);
                }
                
            } else {
                StringPath path = entityPath.getString(k.getNama());
                BooleanExpression expr = null;
                if(EQ.equals(k.getOperator())) {
                    expr = path.eq(k.getNilai().toString());
                } else if(CONTAINS.equals(k.getOperator())) {
                    expr = path.containsIgnoreCase(k.getNilai().toString());
                }
                
                if(expr != null) {
                    daftarPredicate.add(expr);
                }
            }
        }
        
        BooleanExpression predicate = daftarPredicate.get(0);
        for(int i = 1; i < daftarPredicate.size(); i++) {
            predicate = predicate.and(daftarPredicate.get(i));
        }
        
        return predicate;
    }
    
    private Boolean isNumeric(Object o){
        try {
            new BigDecimal(o.toString());
            return true;
        } catch (NumberFormatException err){
            return false;
        }
    }
}
