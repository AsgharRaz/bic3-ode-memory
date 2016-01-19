package at.technikum.ode.memory;

import org.junit.Assert;
import org.junit.Test;

import java.io.File;

/**
 * Created by Thomas on 15.01.16.
 */
public class ImageFileProviderTest {


    @Test
    public void testGetImageCount() throws Exception {
        ImageFileProvider provider = new ImageFileProvider("/Users/Thomas/Pictures");

        Assert.assertTrue(0 < provider.getImageCount());
    }

    @Test
    public void testGetImageFiles() throws Exception {
        ImageFileProvider provider = new ImageFileProvider("/Users/Thomas/Pictures");

        File[] imageFiles = provider.getImageFiles();
        Assert.assertNotNull(imageFiles);
        Assert.assertTrue(0 < imageFiles.length);
    }
}