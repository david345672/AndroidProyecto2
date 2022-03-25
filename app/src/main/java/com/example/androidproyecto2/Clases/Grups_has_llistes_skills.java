package com.example.androidproyecto2.Clases;

public class Grups_has_llistes_skills
{
//    private Curs cursos;
//    private Grup grups;
//    private LlistaSkills llistes_skills;
    private int grups_id;
    private int llistes_skills_id;
    private int curs_id;

    public Grups_has_llistes_skills() {
    }

    public Grups_has_llistes_skills(int grups_id, int llistes_skills_id, int curs_id) {
        this.grups_id = grups_id;
        this.llistes_skills_id = llistes_skills_id;
        this.curs_id = curs_id;
    }

//    public Grups_has_llistes_skills(Curs cursos, Grup grups, LlistaSkills llistes_skills, int grups_id, int llistes_skills_id, int curs_id) {
//        this.cursos = cursos;
//        this.grups = grups;
//        this.llistes_skills = llistes_skills;
//        this.grups_id = grups_id;
//        this.llistes_skills_id = llistes_skills_id;
//        this.curs_id = curs_id;
//    }
//
//    public Curs getCursos() {
//        return cursos;
//    }
//
//    public void setCursos(Curs cursos) {
//        this.cursos = cursos;
//    }
//
//    public Grup getGrups() {
//        return grups;
//    }
//
//    public void setGrups(Grup grups) {
//        this.grups = grups;
//    }
//
//    public LlistaSkills getLlistes_skills() {
//        return llistes_skills;
//    }
//
//    public void setLlistes_skills(LlistaSkills llistes_skills) {
//        this.llistes_skills = llistes_skills;
//    }

    public int getGrups_id() {
        return grups_id;
    }

    public void setGrups_id(int grups_id) {
        this.grups_id = grups_id;
    }

    public int getLlistes_skills_id() {
        return llistes_skills_id;
    }

    public void setLlistes_skills_id(int llistes_skills_id) {
        this.llistes_skills_id = llistes_skills_id;
    }

    public int getCurs_id() {
        return curs_id;
    }

    public void setCurs_id(int curs_id) {
        this.curs_id = curs_id;
    }
}
