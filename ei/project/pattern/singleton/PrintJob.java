package ei.project.pattern.singleton;


public class PrintJob {
    private String documentName;
    private int pages;
    private String orientation;
    private String paperSize;

    public PrintJob(String documentName, int pages, String orientation, String paperSize) {
        this.documentName = documentName;
        this.pages = pages;
        this.orientation = orientation;
        this.paperSize = paperSize;
    }

    public String getDocumentName() {
        return documentName;
    }

    public int getPages() {
        return pages;
    }

    public String getOrientation() {
        return orientation;
    }

    public String getPaperSize() {
        return paperSize;
    }
}
