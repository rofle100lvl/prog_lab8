package presention;

import java.lang.ref.WeakReference;
import java.util.*;

public class LocaleChanger {
    private String path;
    private Locale locale;

    private LinkedList<WeakReference<LocaleChangeable>> subscribers;

    public LocaleChanger(String path) {
        this.path = path;
        subscribers = new LinkedList<>();
    }

    public void changLocale(Locale locale) {
        this.locale = locale;
        notifySubscribers();
    }

    public void addSubscriber(LocaleChangeable localeChangeable) {
        subscribers.add(new WeakReference<LocaleChangeable>(localeChangeable));
        if (Objects.nonNull(locale)) {
            ResourceBundle resourceBundle = ResourceBundle.getBundle(path, locale);
            localeChangeable.localeDidChange(resourceBundle, locale);
        }
    }

    private void notifySubscribers() {
        ResourceBundle resourceBundle = ResourceBundle.getBundle(path, locale);
        Iterator<WeakReference<LocaleChangeable>> iterator = subscribers.iterator();
        while (iterator.hasNext()) {
            WeakReference<LocaleChangeable> sub = iterator.next();
            if (Objects.nonNull(sub.get())) {
                sub.get().localeDidChange(resourceBundle, locale);
            } else {
                System.out.println("weak deleted");
                iterator.remove();
            }
        }
    }

}
