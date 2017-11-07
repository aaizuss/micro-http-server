package com.aaizuss.handler;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class FileTypeReaderTest {
    @Test
    public void testGetContentTypeRecognizesHtml() {
        assertEquals("text/html", FileTypeReader.getContentType("index.html"));
    }

    @Test
    public void testGetContentTypeRecognizesCss() {
        assertEquals("text/css", FileTypeReader.getContentType("styles.css"));
    }

    @Test
    public void testGetContentTypeRecognizesJavascript() {
        assertEquals("text/javascript", FileTypeReader.getContentType("script.js"));
    }

    @Test
    public void testGetContentTypeRecognizesTxt() {
        assertEquals("text/plain", FileTypeReader.getContentType("file.txt"));
    }

    @Test
    public void testGetContentTypeRecognizesPng() {
        assertEquals("image/png", FileTypeReader.getContentType("image.png"));
    }

    @Test
    public void testGetContentTypeReturnsOctetStreamWhenNoExtension() {
        assertEquals("application/octet-stream", FileTypeReader.getContentType("file.mov"));
    }

    @Test
    public void testFileTypeReturnsCorrectEnum() {
        assertEquals(FileTypeReader.FileType.TEXT, FileTypeReader.fileType("index.html"));
        assertEquals(FileTypeReader.FileType.UNSUPPORTED, FileTypeReader.fileType("index.mov"));
        assertEquals(FileTypeReader.FileType.IMAGE, FileTypeReader.fileType("dog.gif"));
    }
}
