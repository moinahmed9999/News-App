package com.moin.newsapp.model;

import java.util.List;
import java.util.Objects;

public class Response {
    private String status;
    private String totalResults;
    private List<Article> articles;

    public String getStatus() {
        return status;
    }

    public String getTotalResults() {
        return totalResults;
    }

    public List<Article> getArticles() {
        return articles;
    }

    public static class Article {
        private Source source;
        private String title;
        private String description;
        private String url;
        private String urlToImage;
        private String publishedAt;

        public Source getSource() {
            return source;
        }

        public String getTitle() {
            return title;
        }

        public String getDescription() {
            return description;
        }

        public String getUrl() {
            return url;
        }

        public String getUrlToImage() {
            return urlToImage;
        }

        public String getPublishedAt() {
            return publishedAt;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Article)) return false;
            Article article = (Article) o;
            return Objects.equals(getSource(), article.getSource()) &&
                    Objects.equals(getTitle(), article.getTitle()) &&
                    Objects.equals(getDescription(), article.getDescription()) &&
                    Objects.equals(getUrl(), article.getUrl()) &&
                    Objects.equals(getUrlToImage(), article.getUrlToImage()) &&
                    Objects.equals(getPublishedAt(), article.getPublishedAt());
        }

        @Override
        public int hashCode() {
            return 0;
        }
    }

    public static class Source {
        private String name;

        public String getName() {
            return name;
        }
    }

}
