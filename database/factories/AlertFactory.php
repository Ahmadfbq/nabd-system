<?php

namespace Database\Factories;

use App\Models\Measurement;
use App\Models\User;
use Illuminate\Database\Eloquent\Factories\Factory;

/**
 * @extends \Illuminate\Database\Eloquent\Factories\Factory<\App\Models\Alert>
 */
class AlertFactory extends Factory
{
    /**
     * Define the model's default state.
     *
     * @return array<string, mixed>
     */
    public function definition(): array
    {
        $measurementTypes = [
            'heart_rate',
            'spo2',
            'bp_systolic',
            'bp_diastolic',
            'glucose',
            'temperature'
        ];
        $measurementType = fake()->randomElement($measurementTypes);
        $alertType = fake()
            ->randomElement([
                'critical',
                'warning',
                'info'
            ]);
        $isAcknowledged = fake()->boolean(30);

        [$value, $threshold, $message] = $this->generateMeasurementData($measurementType, $alertType);

        return [
            'user_id' => User::factory(),
            'measurement_id' => Measurement::factory(),
            'type' => $alertType,
            'measurement_type' => $measurementType,
            'value' => $value,
            'threshold' => $threshold,
            'message' => $message,
            'acknowledged' => $isAcknowledged,
            'acknowledged_at' => $isAcknowledged ? fake()->dateTimeBetween('-1 week', 'now') : null,
            'acknowledged_by' => $isAcknowledged ? User::factory() : null,
        ];
    }

    private function generateMeasurementData(string $measurementType, string $alertType): array
    {
        return match ($measurementType) {
            'heart_rate' => [
                fake()->numberBetween(140, 180), // Simulated value
                120, // Threshold
                "Heart rate {$alertType}: " . fake()->randomElement([ // Messages 
                    'Heart rate too high',
                    'Elevated heart rate detected',
                    'Irregular heart rhythm'
                ])
            ],
            'spo2' => [
                fake()->numberBetween(75, 89), 
                90, 
                "SpO2 {$alertType}: " . fake()->randomElement([ 
                    'Low oxygen saturation detected',
                    'SpO2 levels below normal',
                    'Hypoxemia detected'
                ])
            ],
            'bp_systolic' => [
                fake()->numberBetween(160, 200), 
                140, 
                "Systolic BP {$alertType}: " . fake()->randomElement([
                    'High blood pressure detected',
                    'Elevated systolic pressure',
                    'Hypertension alert'
                ])
            ],
            'bp_diastolic' => [
                fake()->numberBetween(100, 120), 
                90, 
                "Diastolic BP {$alertType}: " . fake()->randomElement([ 
                    'High diastolic pressure detected',
                    'Elevated diastolic pressure',
                    'Hypertension alert'
                ])
            ],
            'glucose' => [
                fake()->randomFloat(2, 250, 400),
                180.00,
                "Glucose {$alertType}: " . fake()->randomElement([
                    'High blood sugar detected',
                    'Elevated glucose levels',
                    'Hyperglycemia detected',
                    'Blood sugar spike detected',
                    'Diabetic Emergency: High glucose levels',
                    'Critical glucose alert: Immediate action required'
                ])
            ],
            'temperature' => [
                fake()->randomFloat(2, 39.0, 42.0),
                38.5,
                "Temperature {$alertType}: " . fake()->randomElement([ 
                    'Elevated body temperature',
                    'Hyperthermia alert',
                    'Critical temperature alert: Seek medical attention immediately',
                    'High fever detected',
                    'Fever alert: Body temperature above normal',
                ])
            ]
        };
    }
}
