package callback;

import model.Model;

public interface AutomataCallback {
    void createFAListener(Model model);
    void acceptedStringListener();
}
