<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Model;

class Measurement extends Model
{
    protected $fillable = [
        'user_id',
        'heart_rate',
        'spo2',
        'bp_systolic',
        'bp_diastolic',
        'glucose',
        'temperature',
        'recorded_at',
    ];

    protected function casts(): array
    {
        return [
            'glucose' => 'decimal:2',
            'temperature' => 'decimal:2',
            'recorded_at' => 'datetime',
        ];
    }

    public function user()
    {
        return $this->belongsTo(User::class);
    }

    public function alerts()
    {
        return $this->hasMany(Alert::class);
    }

}
