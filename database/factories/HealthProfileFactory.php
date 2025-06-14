<?php

namespace Database\Factories;

use App\Models\User;
use Illuminate\Database\Eloquent\Factories\Factory;

/**
 * @extends \Illuminate\Database\Eloquent\Factories\Factory<\App\Models\HealthProfile>
 */
class HealthProfileFactory extends Factory
{
    /**
     * Define the model's default state.
     *
     * @return array<string, mixed>
     */
    public function definition(): array
    {
        $firstName = fake()->firstName();
        $lastName = fake()->lastName();
        $fullName = $firstName . ' ' . $lastName;

        return [
            'user_id' => User::factory(),
            'emergency_contact_name' => $fullName,
            'emergency_contact_phone' => fake()->phoneNumber(),
            'emergency_contact_email' => strtolower($firstName . '.' . $lastName . '@example.com'),
            'heart_rate_min' => fake()->numberBetween(60, 80),
            'heart_rate_max' => fake()->numberBetween(95, 120),
            'spo2_min' => fake()->numberBetween(90, 95),
            'bp_systolic_max' => fake()->numberBetween(120, 140),
            'bp_diastolic_max' => fake()->numberBetween(80, 90),
            'glucose_min' => fake()->randomFloat(2, 70, 90),
            'glucose_max' => fake()->randomFloat(2, 100, 200),
            'temperature_min' => fake()->randomFloat(2, 35.0, 36.5),
            'temperature_max' => fake()->randomFloat(2, 37.5, 39.0),
            'temperature_emergency' => fake()->randomFloat(2, 39.5, 40.5),
            'notifications_enabled' => fake()->boolean(75),
            'emergency_alerts_enabled' => fake()->boolean(60),
        ];
    }
}
