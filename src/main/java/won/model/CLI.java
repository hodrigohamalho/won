package won.model;

/**
 * @author Rodrigo Ramalho
 *         hodrigohamalho@gmail.com.
 *
 */
public class CLI {

    public CLI() {}

    public CLI(String url, boolean recursive, boolean runtime) {
        this.url = url;
        this.recursive = recursive;
        this.runtime = runtime;
    }

    private String url;
    private boolean recursive;
    private boolean runtime;

    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isRecursive() {
        return recursive;
    }
    public void setRecursive(boolean recursive) {
        this.recursive = recursive;
    }

    public boolean isRuntime() {
        return runtime;
    }
    public void setRuntime(boolean runtime) {
        this.runtime = runtime;
    }
}
