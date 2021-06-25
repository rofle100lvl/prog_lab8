package presention;

import java.util.Locale;
import java.util.ResourceBundle;

public interface LocaleChangeable {
    void localeDidChange(ResourceBundle resourceBundle, Locale locale);
}
