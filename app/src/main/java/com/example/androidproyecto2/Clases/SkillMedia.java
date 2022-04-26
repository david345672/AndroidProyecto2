package com.example.androidproyecto2.Clases;

public class SkillMedia
{
    private String nomSkill;
    private float media;

    public SkillMedia(String nomSkill, float media) {
        this.nomSkill = nomSkill;
        this.media = media;
    }

    public String getNomSkill() {
        return nomSkill;
    }

    public float getMedia() {
        return media;
    }


    @Override
    public String toString() {
        return "SkillMedia{" +
                "nomSkill='" + nomSkill + '\'' +
                ", media=" + media +
                '}';
    }
}
