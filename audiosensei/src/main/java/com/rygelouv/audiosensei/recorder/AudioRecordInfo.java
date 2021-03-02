package com.rygelouv.audiosensei.recorder;

import android.app.Activity;
import android.os.Environment;
import android.support.annotation.IntDef;
import android.util.Log;

import java.io.File;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import static android.os.Environment.DIRECTORY_MUSIC;
import static com.rygelouv.audiosensei.recorder.AudioRecordInfo.AudioPath.APP_PRIVATE_AUDIO;
import static com.rygelouv.audiosensei.recorder.AudioRecordInfo.AudioPath.APP_PUBLIC_MUSIC;
import static com.rygelouv.audiosensei.recorder.AudioRecordInfo.AudioPath.PHONE_PUBLIC_MUSIC;
import static com.rygelouv.audiosensei.recorder.AudioRecordInfo.AudioType.TYPE_MP3;
import static com.rygelouv.audiosensei.recorder.AudioRecordInfo.AudioType.TYPE_AAC;
import static com.rygelouv.audiosensei.recorder.AudioRecordInfo.AudioType.TYPE_3GP;

/**
 Created by rygelouv on 3/6/18.
 Copyright 2017 Rygelouv.

 Licensed under the Apache License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at

 http://www.apache.org/licenses/LICENSE-2.0

 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
 **/

public class AudioRecordInfo
{
    protected String extension = ".mp3";

    public String name;
    public Activity activity;
    public @AudioPath int path;
    public @AudioType int type;

    @IntDef({PHONE_PUBLIC_MUSIC, APP_PUBLIC_MUSIC, APP_PRIVATE_AUDIO,})
    @Retention(RetentionPolicy.SOURCE)
    public @interface AudioPath{
        int PHONE_PUBLIC_MUSIC = 1;
        int APP_PUBLIC_MUSIC = 2;
        int APP_PRIVATE_AUDIO = 3;
    }

    public String getProperPath()
    {
        switch (path)
        {
            case AudioPath.PHONE_PUBLIC_MUSIC:
                return Environment.getExternalStoragePublicDirectory(DIRECTORY_MUSIC).getAbsolutePath() + File.separator + name + getExtension();
            case AudioPath.APP_PUBLIC_MUSIC:
                return activity.getExternalFilesDir(DIRECTORY_MUSIC).getAbsolutePath() + File.separator + name + getExtension();
            case AudioPath.APP_PRIVATE_AUDIO:
                Log.e("GAG", activity.getFilesDir().getAbsolutePath() + File.separator + "audios" + File.separator + name + getExtension());
                return activity.getFilesDir().getAbsolutePath() + File.separator + "audios" + File.separator + name + getExtension();
            default:
                return Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + name + getExtension();
        }
    }
 
    @IntDef({TYPE_MP3, TYPE_AAC, TYPE_3GP,})
    @Retention(RetentionPolicy.SOURCE)
    public @interface AudioType{
        int TYPE_MP3 = 1;
        int TYPE_AAC = 2;
        int TYPE_3GP = 3;
    }
 
    private String getExtension() {
        switch (type)
        {
            case AudioType.TYPE_MP3:
                extension = ".mp3";
            case AudioType.TYPE_AAC:
                extension = ".aac";
            case AudioType.TYPE_3GP:
                extension = ".3gp";
            default:
                extension = ".mp3";
        }
        return extension;
    }
}
