package com.example.luis.qroom;

public class Sala {

    private String _id;
    private String nome;
    private String bloco;
    private String decricao;
    private String url;

    public Sala() {

    }

    public Sala(String _id, String nome, String bloco, String decricao, String url) {
        this._id = _id;
        this.nome = nome;
        this.bloco = bloco;
        this.decricao = decricao;
        this.url = url;
    }

    public String get_id() { return _id; }
    public void set_id(String _id) {
        this._id = _id;
    }

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getBloco() {
        return bloco;
    }
    public void setBloco(String bloco) {
        this.bloco = bloco;
    }

    public String getDecricao() {
        return decricao;
    }
    public void setDecricao(String decricao) {
        this.decricao = decricao;
    }

    public String getUrl() { return url; }
    public void setUrl(String url) { this.url = url; }
}
