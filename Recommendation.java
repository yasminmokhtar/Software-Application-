package Entity;

import java.util.ArrayList;
import java.util.List;

public class Recommendation {
    private String recId;
    private List<String> suggestions = new ArrayList<>();

    public Recommendation(String recId, List<String> suggestions) {
        this.recId = recId;
        this.suggestions = suggestions;
    }

    public void setRecId(String recId) {
        this.recId = recId;
    }

    public void setSuggestions(List<String> suggestions) {
        this.suggestions = suggestions;
    }

    public String getRecId() {
        return recId;
    }

    public List<String> getSuggestions() {
        return suggestions;
    }
}
