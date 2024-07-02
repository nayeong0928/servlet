package hello.servlet.basic.web.frontcontroller;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class ModelView {

    private String viewNm;
    private Map<String, Object> models=new HashMap<>();

    public ModelView(String viewNm) {
        this.viewNm = viewNm;
    }
}
