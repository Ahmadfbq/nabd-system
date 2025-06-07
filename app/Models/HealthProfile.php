<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Model;

class HealthProfile extends Model
{
    protected $fillable = [
        'user_id',
        'emergency_contact_name',
        'emergency_contact_phone',
        'emergency_contact_email',
        'heart_rate_min',
        'heart_rate_max',
        'spo2_min',
        'bp_systolic_max',
        'bp_diastolic_max',
        'glucose_min',
        'glucose_max',
        'temperature_min',
        'temperature_max',
        'temperature_emergency',
        'notifications_enabled',
        'emergency_alerts_enabled',
    ];

    /**
     * Get the attributes that should be cast.
     *
     * @return array<string, string>
     */
    protected function casts(): array
    {
        return [
            'notifications_enabled' => 'boolean',
            'emergency_alerts_enabled' => 'boolean',
            'glucose_min' => 'decimal:2',
            'glucose_max' => 'decimal:2',
            'temperature_min' => 'decimal:2',
            'temperature_max' => 'decimal:2',
            'temperature_emergency' => 'decimal:2',
        ];
    }

    public function user()
    {
        return $this->belongsTo(User::class);
    }
}
