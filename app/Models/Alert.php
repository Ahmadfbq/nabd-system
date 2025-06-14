<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class Alert extends Model
{
    use HasFactory;
    
    protected $fillable = [
        'user_id',
        'measurement_id',
        'type',
        'measurement_type',
        'value',
        'threshold',
        'message',
        'acknowledged',
        'acknowledged_at',
        'acknowledged_by',
    ];

    protected function casts(): array
    {
        return [
            'acknowledged' => 'boolean',
            'acknowledged_at' => 'datetime',
            'value' => 'decimal:2',
            'threshold' => 'decimal:2',
        ];
    }

    public function user()
    {
        return $this->belongsTo(User::class);
    }

    public function measurement()
    {
        return $this->belongsTo(Measurement::class);
    }

    public function acknowledgedBy()
    {
        return $this->belongsTo(User::class, 'acknowledged_by');
    }
}
