package at.technikum.ode.memory;

import java.io.File;

/**
 * Created by Thomas on 11.01.16.
 */

/**
 * Provider class to return all supported image files in the given path
 */
public final class ImageFileProvider {

    private File dirRoot;

    /**
     *
     * @param path The path of the directory containing the image files
     */
    public ImageFileProvider(String path) {
        setImageRootPath(path);
    }

    /**
     *
     * @param path The path of the directory containing the image files
     */
    public void setImageRootPath(String path) {
        dirRoot = new File(path);
    }

    public String getImageRootPath() {
        if (dirRoot == null) return "";
        return dirRoot.getPath();
    }

    /**
     *
     * @return the number of image files found in the given directory
     */
    public int getImageCount() {
        return getImageFiles().length;
    }

    /**
     *
     * @return an array of PNG or JPG image files in the directory specified by path
     */
    public File[] getImageFiles() {
        if ((! dirRoot.exists()) || (! dirRoot.isDirectory())) {
            return new File[]{};
        }
        // enumerate all images in directory
        return dirRoot.listFiles((dir, name) -> {
            if (name.toLowerCase().endsWith(".png") || name.toLowerCase().endsWith(".jpg")) return true;
            return false;
        });
    }
}
