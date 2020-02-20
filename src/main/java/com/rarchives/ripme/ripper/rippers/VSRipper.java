public class VSRipper extends AbstractHTMLRipper {
	
	public VSRipper(URL url) throws IOException {
		super(url);
	}
	
	@Override
    public String getHost() {
        return "victoriassecret";
    }
	
	@Override
    public String getDomain() {
        return "victoriassecret.com";
    }
	
	@Override
    public String getGID(URL url) throws MalformedURLException {
        Pattern p = Pattern.compile("^https?://victoriassecret\\.com/g/([a-z]+)/([a-z]+)/?");
        Matcher m = p.matcher(url.toExternalForm());
        if (m.matches()) {
            return m.group(2);
        }
        throw new MalformedURLException("Expected URL format: " +
                        "victoriassecret.com/vs/____/albumID - got " + url + " instead");
    }
	
	@Override
    public Document getFirstPage() throws IOException {
        // "url" is an instance field of the superclass
        return Http.url(url).get();
    }
	
	@Override
    public List<String> getURLsFromPage(Document doc) {
        List<String> result = new ArrayList<String>();
        for (Element el : doc.select("img")) {
            result.add(el.attr("src"));
        }
        return result
    }
	
	@Override
    public void downloadURL(URL url, int index) {
        addURLToDownload(url, getPrefix(index));
    }
	
}
