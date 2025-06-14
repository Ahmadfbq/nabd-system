<?php

namespace Database\Factories;

use App\Models\User;
use Illuminate\Database\Eloquent\Factories\Factory;

/**
 * @extends \Illuminate\Database\Eloquent\Factories\Factory<\App\Models\Measurement>
 */
class MeasurementFactory extends Factory
{
    /**
     * Define the model's default state.
     *
     * @return array<string, mixed>
     */
    public function definition(): array
    {
        $measurementType = fake()->randomElement([
            'heart_rate', 
            'spo2', 
            'bp_systolic', 
            'bp_diastolic', 
            'glucose', 
            'temperature'
        ]);

        $data = [
            'user_id' => User::factory(),
            'heart_rate' => null,
            'spo2' => null,
            'bp_systolic' => null,
            'bp_diastolic' => null,
            'glucose' => null,
            'temperature' => null,
            'recorded_at' => fake()->dateTimeBetween('-1 year', 'now'),
        ];

        match($measurementType) {
            'heart_rate' => $data['heart_rate'] = fake()->numberBetween(60, 120),
            'spo2' => $data['spo2'] = fake()->numberBetween(90, 100),
            'bp_systolic' => $data['bp_systolic'] = fake()->numberBetween(90, 160),
            'bp_diastolic' => $data['bp_diastolic'] = fake()->numberBetween(60, 100),
            'glucose' => $data['glucose'] = fake()->randomFloat(2, 70, 200),
            'temperature' => $data['temperature'] = fake()->randomFloat(2, 35.0, 40.0),
        };

        return $data;
    }
}
