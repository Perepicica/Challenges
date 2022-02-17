package stream.stepic.grouping;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

class XmlUtils {

    /*
    Method that accepts a list of XML files and a tag name
    produces a map whose keys are the XML file encoding
     and the corresponding values are the total numbers of tags in the files that contain the specified tag.
     If the XML file tags do not contain the specified tag name, the value should be equal to 0.
     */
    public static Map<String, Long> countAllByTagName(List<XmlFile> files, String tagName) {
        return files.stream().collect(
                Collectors.groupingBy(XmlFile::getEncoding,
                        Collectors.summingLong(xml -> xml.contains(tagName) ? xml.getTags().size() : 0)
                ));
        /*return files.stream()
                .collect(Collectors.groupingBy(XmlFile::getEncoding, filtering(
                        x -> x.getTags().stream().anyMatch(tag -> tag.getName().equals(tagName)),
                        summingLong(x -> x.getTags().size()))
                        )
                );*/
    }
}

class Tag {
    private final String name;

    public Tag(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

class XmlFile {
    private final String id;
    private final String encoding;
    private final List<Tag> tags;

    public XmlFile(String id, String encoding, List<Tag> tags) {
        this.id = id;
        this.encoding = encoding;
        this.tags = tags;
    }

    public String getId() {
        return id;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public String getEncoding() {
        return encoding;
    }

    public boolean contains(String tagname) {
        return tags.stream().anyMatch(tag -> tag.getName().equals(tagname));
    }
}