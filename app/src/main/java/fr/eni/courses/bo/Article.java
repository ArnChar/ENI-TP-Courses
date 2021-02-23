package fr.eni.courses.bo;

import android.os.Parcel;
import android.os.Parcelable;

public class Article implements Parcelable {

    private long id = -1;
    private String nom = "";
    private String description = "";
    private String url = "";
    private Double note = 0.0;
    private Double prix = 0.0;
    private boolean achete = false;

    public Article() {
        super();
    }

    public Article(String nom, String description, String url, Double note, Double prix, boolean achete) {
        this.nom = nom;
        this.description = description;
        this.url = url;
        this.note = note;
        this.prix = prix;
        this.achete = achete;
    }

    // GETTERS SETTERS

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Double getNote() {
        return note;
    }

    public void setNote(Double note) {
        this.note = note;
    }

    public Double getPrix() {
        return prix;
    }

    public void setPrix(Double prix) {
        this.prix = prix;
    }

    public boolean isAchete() {
        return achete;
    }

    public void setAchete(boolean achete) {
        this.achete = achete;
    }

    // TOSTRING

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", description='" + description + '\'' +
                ", url='" + url + '\'' +
                ", note=" + note +
                ", prix=" + prix +
                ", achete=" + achete +
                '}';
    }

    // IMPLEMENTATION Parcelable

    protected Article(Parcel in) {
        id = in.readLong();
        nom = in.readString();
        description = in.readString();
        url = in.readString();
        if (in.readByte() == 0) {
            note = null;
        } else {
            note = in.readDouble();
        }
        if (in.readByte() == 0) {
            prix = null;
        } else {
            prix = in.readDouble();
        }
        achete = in.readByte() != 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeString(nom);
        dest.writeString(description);
        dest.writeString(url);
        if (note == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeDouble(note);
        }
        if (prix == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeDouble(prix);
        }
        dest.writeByte((byte) (achete ? 1 : 0));
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Article> CREATOR = new Creator<Article>() {
        @Override
        public Article createFromParcel(Parcel in) {
            return new Article(in);
        }

        @Override
        public Article[] newArray(int size) {
            return new Article[size];
        }
    };

}
