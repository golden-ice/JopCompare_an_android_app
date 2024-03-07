package edu.gatech.seclass.jobcompare6300;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


import java.util.ArrayList;

public class DatabaseController extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "comparejobs.db";
    private static final String TABLE_SETTINGS = "compareSettings";
    private static final String TABLE_JOBS = "jobOffers";


    private static final String CREATE_COMPARISON_SETTINGS_TABLE = "CREATE TABLE " +
            "IF NOT EXISTS " + TABLE_SETTINGS +
            "(ID integer primary key autoincrement, " +
            "ANNUAL_SALARY_WEIGHT integer not null, " +
            "ANNUAL_BONUS_WEIGHT integer not null, " +
            "RETIREMENT_BENEFITS_WEIGHT integer not null, " +
            "RELOCATION_STIPEND_WEIGHT integer not null, " +
            "RSU_AWARD_WEIGHT integer not null);";

    private static final String CREATE_JOB_OFFERS_TABLE = "CREATE TABLE " +
            "IF NOT EXISTS " + TABLE_JOBS +
            "(ID integer primary key autoincrement, " +
            "TITLE text not null, " +
            "COMPANY text not null, " +
            "CITY text not null, " +
            "STATE text not null, " +
            "COST_OF_LIVING integer not null, " +
            "ANNUAL_SALARY numeric not null, " +
            "ANNUAL_BONUS numeric not null, " +
            "RETIREMENT_BENEFITS integer not null, " +
            "RELOCATION_STIPEND numeric not null, " +
            "RSU_AWARD numeric not null, " +
            "SCORE numeric not null, " +
            "IS_CURRENT_JOB integer not null);";

    public void saveJob(Job job)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = getContentValuesFromJob(job);
        db.insert(TABLE_JOBS, null, values);
    }

    public void updateCurrentJob(Job job)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = getContentValuesFromJob(job);
        db.update(TABLE_JOBS, values, "IS_CURRENT_JOB = 1", null);
    }

    public void saveComparisonWeights(ComparisonSettings comparisonSettings)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = getContentValuesFromSettings(comparisonSettings);
        db.insert(TABLE_SETTINGS,  null, values);
    }
    public void updateComparisonWeights(ComparisonSettings comparisonSettings)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = getContentValuesFromSettings(comparisonSettings);
        db.update(TABLE_SETTINGS,  values, "ID = 0", null);
    }

    public ComparisonSettings fetchComparisonSettings()
    {
        SQLiteDatabase db = this.getReadableDatabase();
        final Cursor cursor = db.query(TABLE_SETTINGS, null, "ID = 0", null, null,null, null);
        return getSettingsFromCursor(cursor);
    }

    public Job getJobDetails(Integer id)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        final Cursor cursor = db.query(TABLE_JOBS,null,"ID = ?", new String[] { String.valueOf(id) }, null,null,null);
        return getJobFromCursor(cursor);
    }

    public Job fetchCurrentJob()
    {
        SQLiteDatabase db = this.getReadableDatabase();
        final Cursor cursor = db.query(TABLE_JOBS, null, "IS_CURRENT_JOB = 1", null, null, null, null);
        return getJobFromCursor(cursor);
    }


    public ArrayList<Job> fetchAllJobOffers()
    {
        ArrayList<Job> jobs = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        int rowNo = 0;
        Cursor cursor = db.query(TABLE_JOBS, null, null, null, null, null, null);
        while( rowNo < cursor.getCount()){
            jobs.add(getJobFromCursor(cursor));
            rowNo++;
//            cursor = db.query(TABLE_JOBS, null, , null, null, null, null);
        }
        return jobs;
    }

    public Job fetchJobByJobId(int jobId)
    {
        Job job = null;
        SQLiteDatabase db = this.getReadableDatabase();
        final Cursor cursor = db.query(TABLE_JOBS, null, "IS_CURRENT_JOB = "+jobId, null, null, null, null);
        if(cursor.getCount() > 0) return getJobFromCursor(cursor);
        return job;
    }

    public Job getJobMaxID()
    {
        SQLiteDatabase db = this.getReadableDatabase();
        final Cursor cursor = db.query(TABLE_JOBS, null, null, null, null, null, "id DESC");
        return getJobFromCursor(cursor);
    }

    public DatabaseController(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        SQLiteDatabase appDataBase = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase appDataBase)
    {
        appDataBase.execSQL(CREATE_COMPARISON_SETTINGS_TABLE);
        appDataBase.execSQL(CREATE_JOB_OFFERS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase appDataBase, int oldVersion, int newVersion)
    {
        appDataBase.execSQL("DROP TABLE IF EXISTS " + TABLE_SETTINGS);
        appDataBase.execSQL("DROP TABLE IF EXISTS " + TABLE_JOBS);
        this.onCreate(appDataBase);
    }

    private ContentValues getContentValuesFromSettings(ComparisonSettings cs) {
        final ContentValues data = new ContentValues();
        data.put("ID", cs.getId());
        data.put("ANNUAL_SALARY_WEIGHT", cs.getWeightYearlySalary());
        data.put("ANNUAL_BONUS_WEIGHT", cs.getWeightYearlyBonus());
        data.put("RELOCATION_STIPEND_WEIGHT", cs.getWeightRelocationStipend());
        data.put("RETIREMENT_BENEFITS_WEIGHT", cs.getWeightRetirementBenefits());
        data.put("RSU_AWARD_WEIGHT", cs.getWeightRSUAward());
        return data;
    }

    private ContentValues getContentValuesFromJob(Job job)
    {
        final ContentValues data = new ContentValues();
        data.put("ID", job.getJobID());
        data.put("TITLE", job.getTitle());
        data.put("COMPANY", job.getCompany());
        data.put("CITY", job.getLocationCity());
        data.put("STATE", job.getLocationState());
        data.put("COST_OF_LIVING", job.getLivingCost());
        data.put("ANNUAL_SALARY", job.getYearlySalary());
        data.put("ANNUAL_BONUS", job.getYearlyBonus());
        data.put("RETIREMENT_BENEFITS", job.getRetirementBenefits());
        data.put("RELOCATION_STIPEND", job.getRelocationStipend());
        data.put("RSU_AWARD", job.getRSUAward());
        data.put("SCORE", job.getScore());
        data.put("IS_CURRENT_JOB",job.isCurrentJob());
        return data;
    }

    private Job getJobFromCursor(Cursor cursor)
    {
        cursor.moveToNext();
        if(cursor.getCount() < 1) return null;
        Integer id = cursor.getInt(cursor.getColumnIndex("ID"));
        String title = cursor.getString(cursor.getColumnIndex("TITLE"));
        String company = cursor.getString(cursor.getColumnIndex("COMPANY"));
        String city = cursor.getString(cursor.getColumnIndex("CITY"));
        String state = cursor.getString(cursor.getColumnIndex("STATE"));
        Integer livingCost = cursor.getInt(cursor.getColumnIndex("COST_OF_LIVING"));
        Float annualSalary = cursor.getFloat(cursor.getColumnIndex("ANNUAL_SALARY"));
        Float annualBonus = cursor.getFloat(cursor.getColumnIndex("ANNUAL_BONUS"));
        Integer retirementBenefits = cursor.getInt(cursor.getColumnIndex("RETIREMENT_BENEFITS"));
        Float relocationStipend = cursor.getFloat(cursor.getColumnIndex("RELOCATION_STIPEND"));
        Float RSUAward = cursor.getFloat(cursor.getColumnIndex("RSU_AWARD"));
        Float score = cursor.getFloat(cursor.getColumnIndex("SCORE"));
        boolean isCurrentJob = cursor.getInt(cursor.getColumnIndex("IS_CURRENT_JOB")) > 0;
        return new Job(id, title, company, city, state, livingCost, annualSalary, annualBonus, retirementBenefits, relocationStipend, RSUAward, isCurrentJob, score);
    }

    private ComparisonSettings getSettingsFromCursor(Cursor cursor) {
        cursor.moveToFirst();
        if(cursor.getCount() < 1) return null;
        Integer salaryWeight = cursor.getInt(cursor.getColumnIndex("ANNUAL_SALARY_WEIGHT"));
        Integer annualBonusWeight = cursor.getInt(cursor.getColumnIndex("ANNUAL_BONUS_WEIGHT"));
        Integer retirementBenefitsWeight = cursor.getInt(cursor.getColumnIndex("RETIREMENT_BENEFITS_WEIGHT"));
        Integer relocationStipendWeight = cursor.getInt(cursor.getColumnIndex("RELOCATION_STIPEND_WEIGHT"));
        Integer rsuAwardWeight = cursor.getInt(cursor.getColumnIndex("RSU_AWARD_WEIGHT"));
        return new ComparisonSettings(salaryWeight, annualBonusWeight, retirementBenefitsWeight, relocationStipendWeight, rsuAwardWeight);
    }
}
